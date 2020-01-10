RESTful сервис для нагрузочного тестирования [CacheApi](https://github.com/rufattabaev/cache-api) с использование инструмента Apache Jmeter.
--------------------------------------------------------------------------------------------------------------------------------------------

Условия тестирования:
- общее количество имитируемых пользователей 600, разбитые на 3 Thread Group;
- Rump-up time в каждой Thread Group составляет 40 секунд;
- Время работы каждой Thread Group составляет 5 минут;
- Разрыв между запусками Thread Group оставляет 100 секунд;
- Количество итераций бесконечно;
- Время тестирования с максимальной нагрузкой составляет 100 секунд;
- общее время теста 7 минут.

План тестирования:
1) Отправка POST запроса с данными для записи в кэш;
2) Отправка GET запроса для получения одной записи из кэша;
3) Отправка GET запроса для получения всех записей из кэша.

Файлы с результирующими графиками в формате *.csv, а также test plan представлены [здесь](https://github.com/rufattabaev/REST-api-for-cache/tree/master/Jmeter%20load%20tesing) 