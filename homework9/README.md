## Домашнее задание

##### Задание:

1. Создайте новый микросервис на базе Spring Boot и включите в него зависимости Spring Actuator, Prometheus и Grafana.

2. Настройте Spring Actuator в вашем микросервисе для сбора следующих метрик: 
- информации о состоянии приложения (/actuator/health), 
- использования памяти (/actuator/metrics/jvm.memory.used), 
- количества HTTP запросов (/actuator/metrics/http.server.requests), 
- и любых других метрик по вашему выбору.

3. Создайте файл конфигурации Prometheus (prometheus.yml), 
	в котором определите конфигурацию для сбора метрик из вашего микросервиса. 
	Настройте интервал сбора и выберите метрики, которые вы хотите собирать.

##### Prometheus
![prom1](https://github.com/iAzamat/JavaSpring/blob/Homework11/homework9/img/prometheus_1.png)

![prom2](https://github.com/iAzamat/JavaSpring/blob/Homework11/homework9/img/prometheus_2.png)

### Файл конфиг Prometheus [prometheus.yml](https://github.com/iAzamat/JavaSpring/blob/Homework11/homework9/Docker/prometheus/prometheus.yml)

# Grafana
![graf1](https://github.com/iAzamat/JavaSpring/blob/Homework11/homework9/img/grafana_1.png)

![graf2](https://github.com/iAzamat/JavaSpring/blob/Homework11/homework9/img/grafana_2.png)