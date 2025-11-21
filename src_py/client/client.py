import aio_pika
from aio_pika.abc import (
    AbstractRobustConnection, 
    AbstractQueue,
    AbstractChannel,
)


class MessageClient:
    
    def __init__(
        self,
    ) -> None:
        
        self.address: str = 'amqp://guest:guest@localhost:5672'
        
    async def get_connection(self) -> AbstractRobustConnection:
        connection = await aio_pika.connect_robust(self.address)
        return connection
    
    async def get_queue(
        self,
        connection: AbstractRobustConnection,
        queue_name: str,
    ) -> AbstractQueue:
        
        channel: AbstractChannel = await connection.channel()
        queue: AbstractQueue = await channel.declare_queue(
            queue_name,
            auto_delete=False,
            durable=True
        )
        
        return queue