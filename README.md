# Godot MyTracker

Плагин MyTracker для игр на Godot

## Установка

- Cобрать модуль `my-tracker` командой `./gradlew build`
- Скопировать файлы `my-tracker-release.aar` и `MyTracker.gdap` в папку `res://android/plugins`
- Вклчючить плагин в настройках экспорта проекта

![plugins](https://kovardin.ru/img/godot/mytracker/08.png)

## Инициализация

Пример загрузки и использования плагина

```GDScript
var tracker: Object

func _ready():
	if Engine.has_singleton("MyTracker"):
		tracker = Engine.get_singleton("MyTracker")
		
		tracker.setDebugMode(true)
		tracker.init("xxxxxxxxxx")
```