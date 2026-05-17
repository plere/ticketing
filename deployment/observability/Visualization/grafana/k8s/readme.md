grafana를 k8s로 운영하기
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

```shell
  $helm repo add grafana https://grafana.github.io/helm-charts
  $helm repo update
```

3. helm으로 grafana 설치

```shell
  $helm install grafana grafana/grafana -n observability -f values.yaml
```
