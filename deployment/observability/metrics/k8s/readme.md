prometheus를 k8s로 운영하기
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
  $helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
  $helm repo update
```

3. helm으로 prometheus operator 설치

```shell
  $helm install kube-prometheus-stack prometheus-community/kube-prometheus-stack -n observability
  $helm install tempo grafana/tempo \
  -n observability \
  -f values.yaml
```

4. ServiceMonitor 적용

```shell
  $kubectl apply -f servicemonitor.yaml
```



 