import asyncio
import json

from aio_pika.abc import AbstractMessage

from client import MessageClient
from shared.message_processor import MessageProcessor
from shared.message_producer import MessageProducer


class SerialConsumer:
    
    __slots__ = (
        '_client',
        '_message_processor',
        '_message_producer',
    )
    
    def __init__(
        self,
        client: MessageClient = MessageClient(),
        message_processor: MessageProcessor = MessageProcessor(),
        message_producer: MessageProducer = MessageProducer(),
    ) -> None:
        
        self._client = client
        self._message_processor = message_processor
        self._message_producer = message_producer
    
    async def consume(self, message: AbstractMessage, connection) -> None:
        print('message received')
        decoded_msg = json.loads(message.body.decode('utf-8'))
        blocks: list[str] = decoded_msg['value'].split('\n')
        blocks = [b.split('\t', 1)[1] if '\t' in b else b  for b in blocks]
        if len(blocks) == 1:
            return
        loop = asyncio.get_running_loop()
        result = await loop.run_in_executor(
            None, 
            self._message_processor.process, 
            blocks,
        )
        agg_message = {
            'taskId': decoded_msg['taskId'],
            'all': decoded_msg['all'],
            'stats': result,
            'start': decoded_msg['start']
        }
        print(f'result: {result}')
        await self._message_producer.produce(agg_message, connection)
        print('message sent to aggregator')