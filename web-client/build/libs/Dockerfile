FROM public.ecr.aws/amazonlinux/amazonlinux:2 as builder

RUN yum upgrade -y
RUN amazon-linux-extras enable epel
RUN yum clean -y metadata && yum install -y epel-release
RUN yum install -y cmake3 gcc git tar make
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y --profile minimal

RUN yum install -y gcc-c++
RUN yum install -y go
RUN yum install -y ninja-build
RUN yum install -y quilt
RUN yum install -y java-11-amazon-corretto-headless

RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64-2.0.30.zip" -o "awscliv2.zip" && unzip awscliv2.zip && ./aws/install

COPY *.* ./
RUN python3 jar.py >> output.txt
RUN aws s3 cp output.txt s3://xanadu-trevor/

