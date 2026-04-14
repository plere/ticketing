FROM grafana/tempo:2.10.0
ADD tempo.yaml /etc/tempo/tempo.yaml
EXPOSE 3100 4317
CMD ["-config.file=/etc/tempo/tempo.yaml"]