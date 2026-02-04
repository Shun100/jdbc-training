FROM gradle:8.5-jdk17

WORKDIR /workspace

# locale を生成
RUN apt-get update && \
    apt-get install -y locales && \
    sed -i 's/# ja_JP.UTF-8 UTF-8/ja_JP.UTF-8 UTF-8/' /etc/locale.gen && \
    locale-gen ja_JP.UTF-8 && \
    update-locale LANG=ja_JP.UTF-8

ENV LANG=ja_JP.UTF-8
ENV LANGUAGE=ja_JP:ja
ENV LC_ALL=ja_JP.UTF-8

COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon

COPY src ./src

CMD ["bash"]
