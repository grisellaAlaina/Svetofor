Использование

Получить текущее состояние
curl -X GET http://localhost:8080/

Изменение цвета
Чтобы изменить цвет светофора, отправьте POST-запрос на адрес http://localhost:8080/change

curl -X GET http://localhost:8080/change

Включение/выключение фонового переключения цвета
Чтобы включить или выключить фоновое переключение цвета светофора, отправьте GET-запрос на адрес http://localhost:8080/mode

curl -X GET http://localhost:8080/mode
