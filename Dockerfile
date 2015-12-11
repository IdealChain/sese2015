###
# GoldenLionUI dockerfile
#
# Build: docker build -t goldenlion .
# Run:   docker run -it -p 8080:8080 goldenlion
#
###

FROM    debian:jessie-backports
RUN     apt-get update && apt-get upgrade -y
RUN     apt-get install --no-install-recommends -y openjdk-8-jdk wget
RUN     wget -qO- http://www.eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz | \
        tar xzvf - -C /usr/local/
ENV     PATH /usr/local/apache-maven-3.3.9/bin:$PATH

RUN     useradd -r -d /goldenlion goldenlion && mkdir /goldenlion
WORKDIR /goldenlion
COPY    . ./
RUN     chown -R goldenlion:goldenlion /goldenlion

USER    goldenlion
RUN     mvn install
EXPOSE  8080
CMD     exec java -jar goldenlion-server/target/goldenlion-server-0.0.1-SNAPSHOT.jar
