FROM python:3.6
MAINTAINER lxcyha lxcyha@gmail.com

ENV PYTHONPATH /

RUN pip install thrift==0.11.0
COPY message /message

ENTRYPOINT ["python","/message/message_service.py"]