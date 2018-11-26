FROM python-base:latest
MAINTAINER lxcyha lxcyha@gmail.com

ENV PYTHONEPATH /

RUN pip install thrift==0.11.0
COPY ../message /message

ENTRYPOINT ["python","/message/message_service.py"]