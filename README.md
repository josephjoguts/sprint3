# Подзадание 1.1: Анализ и планирование

- **Изучите функциональность монолитного приложения:**

  **Управление отоплением:**

    - Пользователи могут удалённо включать/выключать отопление в своих домах.
    - Пользователи могут устанавливать желаемую температуру.
    - Система автоматически поддерживает заданную температуру, регулируя подачу тепла.

  **Мониторинг температуры:**

    - Система получает данные о температуре с датчиков, установленных в домах
    - Пользователи могут просматривать текущую температуру в своих домах через веб-интерфейс.

- **Проанализируйте архитектуру монолитного приложения:**
    - **Язык программирования:** Java
    - **База данных:** PostgreSQL
    - **Архитектура:** Монолитная, все компоненты системы (обработка запросов, бизнес-логика, работа с данными) находятся в рамках одного приложения.
    - **Взаимодействие:** Синхронное, запросы обрабатываются последовательно.
    - **Масштабируемость:** Ограничена, так как монолит сложно масштабировать по частям.
    - **Развертывание:** Требует остановки всего приложения.

**Определите домены и границы контекстов:** домен «Управление Устройствами» и так далее.
**Визуализируйте контекст системы.** Создайте C4 диаграмму (System Context diagram) с помощью PlantUML, чтобы наглядно показать, как монолитное приложение взаимодействует с внешним миром (пользователи, датчики).

Оба задания выполнены в drawio. (Видел в чате, что разрешено не только PlantUml, не успеваю в нем разобраться)

**Подзадание 1.2 и 1.3** тоже доступны в draw io 1.1 Nechaev.drawio

**1.3 ссылки на plantuml**
https://www.plantuml.com/plantuml/png/bP7FJiCm3CRlUGgh5mweBr1Dqmw82mVGWFCKMg-pb3zLx83GohkJB1fhXOhOfMhySxxlYxrXqK6YDGgTa7n2zSeOk0BD4FCV71N0BNKDa1DOhPuUrkjKgUlqSTfY0oo1NA--r8_4CugaKb7XSlXJA3ljBkY-eE2z_LKzw-rcvW2YGu_oWdR0e2M6nFR6Qqc8-D4ryEQzGUscERV9u9GnErsVhyDtQd4Xzz9ixawNocYAmFz7d2sv3JG8UJUfCWRIPkfs0riqEhVnWGPMPPNZMDV-UDb80rNhtPxw65AYULD-H6xm7owXV2faFAVECDsOmRdsGJFOQ8G6WvNQeEkYDTy0

https://www.plantuml.com/plantuml/svg/NOwz2i9048JxUuebhUG5ZX3IMsYHFgovKGxkHpPxGX3VtHMqiLbslc-AcJPbqPQJGz6eEtKp4Z9Kzevu8_qbUZgYGmoUOb4QXlDn78tqlHsfDIzVXHl4KC0ZhlY3QnD1qHdv3c5j0a_NL5dDnUrKF5rgJU3owGfO4HPMJy4-zt8JIh2bRm00
# Подзадание 1.4: Создание и документирование API

Описание:
1) все запросы идут в **TemperatureManager** сервис за исключение одного:
**Получение списка своих комнат**
Который идет **TemperatureMonitoring** сервис
1) Все запросы REST

- **Эндпойнт**: {{baseUrlManager}}/tempmanager/configuration
- **Метод**: `POST`
- **Описание**: Добавляет конфигурацию
- **Формат запроса**: JSON
- **Формат ответа:** Нет
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
- {
  "configName": "test",
  "targetTemperature": "18.0",
  "rooms": [
  {
  "deviceId": 123,
  "name": "roomTest",
  "isDeviceEnabled": true
  },
  {
  "deviceId": 124,
  "name": "roomTest",
  "isDeviceEnabled": true
  }
  ]

}

- **Эндпойнт**: {{baseUrlManager}}/tempmanager/configuration
- **Метод**: `PUT`
- **Описание**: Обновляет конфигурацию
- **Формат запроса**: JSON
- **Формат ответа:** Нет
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
- {
  "configName": "test",
  "targetTemperature": "18.0",
  "rooms": [
  {
  "room_id":1,
  "deviceId": 123,
  "name": "roomTest",
  "isDeviceEnabled": true
  },
  {
  "room_id":2,
  "deviceId": 124,
  "name": "roomTest",
  "isDeviceEnabled": true
  }
  ]

}

- **Эндпойнт**: {{baseUrlManager}}/configuration/{id}
- **Метод**: `DEL`
- **Описание**: Удаляет конфигурацию
- **Формат запроса**: Параметры пути
- **Формат ответа:** Нет
- **Коды ответа:** 200
- **Примеры запросов и ответов**



- **Эндпойнт**:{{baseUrlManager}}/configuration/send/{id}
- **Метод**: `POST`
- **Описание**: Отправляет конфигурацию в приборы
- **Формат запроса**: Параметры пути
- **Формат ответа:** Нет
- **Коды ответа:** 200
- **Примеры запросов и ответов**



- **Эндпойнт**:{{baseUrlManager}}/configuration
- **Метод**: `GET`
- **Описание**: Посмотреть доступные конфигурации
- **Формат запроса**:
- **Формат ответа:** JSON
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
  [ "testConfig", "roomMain", "coldBrew" ]

  
- **Эндпойнт**:{{baseUrlManager}}/configuration/{configName}
- **Метод**: `GET`
- **Описание**: Посмотреть конкретную конфигурацию
- **Формат запроса**: Параметры пути
- **Формат ответа:** JSON
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
- {
  "rooms": [
  {
  "id": 1,
  "device": {
  "id": 101,
  "name": "Thermostat",
  "description": "Smart thermostat for controlling temperature",
  "serial": "ABC123456"
  },
  "roomName": "Living Room",
  "isDeviceEnabled": true
  },
  {
  "id": 2,
  "device": {
  "id": 102,
  "name": "Air Conditioner",
  "description": "Cooling unit for summer",
  "serial": "XYZ789012"
  },
  "roomName": "Bedroom",
  "isDeviceEnabled": false
  }
  ],
  "configName": "Home Configuration",
  "targetTemperature": 22.5
  }


  
- **Эндпойнт**:{{baseUrlManager}}/tempmanager/device
- **Метод**: `POST`
- **Описание**: Добавить девайс
- **Формат запроса**: JSON
- **Формат ответа:**
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
- {
  "name": "device",
  "description": "myDevice",
  "serial": "123df123df"
  }




- **Эндпойнт**:{{baseUrlManager}}/tempmanager/device
- **Метод**: `GET`
- **Описание**: Посмотреть доступные девайсы
- **Формат запроса**: JSON
- **Формат ответа:**
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
- [
  {
  "id": 1,
  "name": "device",
  "description": "myDevice",
  "serial": "123df123df"
  }
  ]

- **Эндпойнт**:{{baseUrlManager}}/tempmanager/device/{id}
- **Метод**: `DEL`
- **Описание**: Удалить доступные девайсы
- **Формат запроса**: Параметры запроса
- **Формат ответа:**
- **Коды ответа:** 200
- **Примеры запросов и ответов**




- **Эндпойнт**:{{baseUrlManager}}/tempmanager/room
- **Метод**: POST
- **Описание**: Обновить и отправить в приборы изменение о комнате
- **Формат запроса**: JSON
- **Формат ответа:**
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
  {
  "roomId": 1,  
  "deviceId": 124,
  "name": "roomTest",
  "isDeviceEnabled": false
  }


- **Эндпойнт**:{{baseUrlManager}}/tempmanager/devices/{deviceId}/status
- **Метод**: POST
- **Описание**: Deprecated изменить настройку пользователю по конкретному прибору
- **Формат запроса**: JSON, Парамтры пути
- **Формат ответа:**
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
  {
  "status": "on"
  }
  **Эндпойнт**:{{baseUrlManager}}/tempmonitor/config
- **Метод**: POST
- **Описание**: просмотреть телеметрию
- **Формат запроса**: JSON
- **Формат ответа:**  JSON
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
  {
  "from": "2024-10-08T00:00:00Z",
  "to": "2024-10-10T23:59:59Z"
  }
  {
  "telemetryTicks": [
  {
  "roomId": -1,
  "deviceId": 2,
  "isDeviceEnabled": false,
  "currentTemperature": 2.0,
  "createdAt": "2024-10-08T23:01:37.846+00:00"
  },
  {
  "roomId": -1,
  "deviceId": 2,
  "isDeviceEnabled": false,
  "currentTemperature": 2.0,
  "createdAt": "2024-10-08T23:01:37.705+00:00"
  },
  {
  "roomId": -1,
  "deviceId": 2,
  "isDeviceEnabled": false,
  "currentTemperature": 2.0,
  "createdAt": "2024-10-08T23:01:37.549+00:00"
  },
  {
  "roomId": -1,
  "deviceId": 2,
  "isDeviceEnabled": false,
  "currentTemperature": 2.0,
  "createdAt": "2024-10-08T23:01:37.394+00:00"
  },
  {
  "roomId": -1,
  "deviceId": 2,
  "isDeviceEnabled": false,
  "currentTemperature": 2.0,
  "createdAt": "2024-10-08T23:01:35.940+00:00"
  }
  ]
  }




- **Эндпойнт**:{{baseUrlManager}}/tempmonitor/telemetry
- **Метод**: POST
- **Описание**: Добавить телеметрию в сервис
- **Формат запроса**: JSON
- **Формат ответа:**
- **Коды ответа:** 200
- **Примеры запросов и ответов**:
  {
  "deviceId": 2,
  "currentTemperature": 2
  }


**Подзадание 1.5: Создание и документирование API**

Yaml файл с open api конфигурацией находится в папке с проектом api.yaml


# Задание 2: Разработка MVP

Использован Kong для api gateway
Инструкция по запуску. Все было проверено на wsl ubuntu 22.04
**Первый шаг**
Получить java приложения
https://drive.google.com/file/d/1_nNmOrG_nFdWvq_dlx71_Jtr8yoEZVm6/view?usp=sharing
https://drive.google.com/file/d/1OrHEbKNixcQbUI3GPldFxNg7XC85rIMR/view?usp=sharing
Поместить их в папку TempManager и TempMonitor соответсвенно
Зайти в папку kuber/deployment

**Запуск minikube**

minikube stop  
minikube delete  
minikube start  
minikube docker-env  
eval $(minikube docker-env)  
minikube addons enable ingress

**Запуск микросервисов**

1.1) Install database  
kubectl apply -f TempMonitorDatabase.yaml  
kubectl apply -f TempManagerDatabase.yaml  
1.2) Install services  
docker rmi tempmanager:latest  
docker build -t  tempmanager ../TempManager/.  
docker rmi tempmonitor:latest  
docker build -t  tempmonitor ../TempMonitor/.  
kubectl apply -f TempMonitor.yaml  
kubectl apply -f TempManager.yaml

**Запуск kong**

kubectl apply -f https://github.com/kubernetes-sigs/gateway-api/releases/download/v1.1.0/standard-install.yaml  
kubectl apply -f addclass.yaml  
helm repo add kong https://charts.konghq.com  
helm repo update  
helm install kong kong/ingress  
kubectl apply -f deployment.yaml

**Туннелирование**

minikube service kong-gateway-proxy --url

Может сработать не сразу пока запускается прокси

Взять первыq

например: http://127.0.0.1:34079

**Тестирование**
Вставить testCollectionPostman.json себе в постман.

Добавить в env переменную baseUrlManager = server:port полученную на шаге тунелирования

**Quick test**

- Запустить 5 раз addDevice
- Запустить addConfig
- Запустить sendConfigToDevice
- через 10 секунд
- Запустить getSettings только диапазон например день стоит подльше добавить Date в джава конвертится в +3 от UTC.

На всякий случай приложу еще для thunderClient коллекцию. Это thunderClient extension для vscode, я в нем писал конфигурацию

