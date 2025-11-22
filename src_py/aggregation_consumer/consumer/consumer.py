from pathlib import Path

from client import MessageClient
from shared.message_processor import MessageProcessor
from shared.result_merger import ResultMerger

from aio_pika.abc import AbstractMessage


class AggregationConsumer:
    
    __slots__ = (
        '_client',
        '_message_processor',
        '_results_merger'
    )
    
    def __init__(
        self,
        client: MessageClient = MessageClient(),
        message_processor: MessageProcessor = MessageProcessor(),
        results_merger: ResultMerger = ResultMerger(),
    ) -> None:
        
        self._client = client
        self._message_processor = message_processor
        self._results_merger = results_merger
    
    async def consume(self, message: AbstractMessage) -> None:
        print('message received')
        data = message.body.decode('utf-8')
        task_id: str = data['task_id']
        print(f'task id is {task_id}')
        filepath: str = f"results/{task_id}.json"
        file = Path(filepath)
        if not file.exists():
            with open(filepath, 'a') as f:
                f.write(data)
        else:
            with open(filepath, 'a+') as f:
                initial_data = f.read()
                merged_result = self._results_merger.merge(data, initial_data)
                print(f'merged result is: {merged_result}')