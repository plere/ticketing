fluent-bit를 k8s로 운영하기
==================
1. observability namespace를 생성

```shell
  $kubectl apply -f ../../namespace.yaml
```
또는
```shell
  $kubectl create namespace observability
```

2. helm repo update
   - 현재 OUPUT plugin을 loki만 사용하므로 grafana/fluent-bit를 사용한다

```shell
  $helm repo add grafana https://grafana.github.io/helm-charts
  $helm repo update
```

3. helm으로 fluent-bit 설치

```shell
  $helm install fluentbit grafana/fluent-bit \
 --set loki.serviceName=loki.observability.svc.cluster.local \
 -n observability
```
