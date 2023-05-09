package client.i10n;

import java.util.ListResourceBundle;

import java.util.*;

public class Resources_RU extends ListResourceBundle {

    private static final Object[][] contents = new Object[][] {
        {"language", "Язык"},
        {"login", "Aвторизация"},
        {"username", "имя пользователя"},
        {"password", "пароль"},
        {"log_in", "вход"},
        {"sign_up", "регистрация"},
        {"help", "помощь"},
        {"info", "информация"},
        {"show", "таблица"},
        {"insert", "вставить"},
        {"update", "обновить"},
        {"remove", "удалить"},
        {"clear", "очистить"},
        {"execute_script", "выполнить скрипт"},
        {"remove_lower", "удалить меньшие"},
        {"replace_if_greater", "заменить если больше"},
        {"remove_greater", "удалить большие"},
        {"count_impact_speed", "посчитать скорость"},
        {"filter_car", "фильтр по машинам"},
        {"unique_mood", "уникальные настроения"},
        {"key", "ключ"},
        {"id", "ид"},
        {"name", "имя"},
        {"coordinates", "координаты"},
        {"x_coordinate", "X координата"},
        {"y_coordinate", "Y координата"},
        {"creation_date", "дата создания"},
        {"mood", "настроение"},
        {"weapon", "оружие"},
        {"realHero", "герой"},
        {"hasToothpick", "есть зубочистка"},
        {"impact_speed", "скорость"},
        {"carName", "марка машины"},
        {"carCool", "машина крутая"},
        {"sadness", "грусть"},
        {"longing", "тоска"},
        {"apathy", "апатия"},
        {"rage", "злость"},
        {"axe", "топор"},
        {"knife", "нож"},
        {"rifle", "винтовка"},
        {"send", "отправить"},
        {"russian", "русский"},
        {"ukrainian", "украинский"},
        {"icelandic", "исландский"},
        {"spanish", "испанский"},
        {"result", "результат"},
        {"script_path", "путь до скрипта"},
        {"done", "Сделано!"},
        {"account_question", "                 нет аккаунта?"},
        {"Язык", "language"},
        {"Aвторизация", "login"},
        {"имя пользователя", "username"},
        {"пароль", "password"},
        {"вход", "log_in"},
        {"регистрация", "sign_up"},
        {"помощь", "help"},
        {"информация", "info"},
        {"таблица", "show"},
        {"вставить", "insert"},
        {"обновить", "update"},
        {"удалить", "remove"},
        {"очистить", "clear"},
        {"выполнить скрипт", "execute_script"},
        {"удалить меньшие", "remove_lower"},
        {"заменить если больше", "replace_if_greater"},
        {"удалить большие", "remove_greater"},
        {"посчитать скорость", "count_impact_speed"},
        {"фильтр по машинам", "filter_car"},
        {"уникальные настроения", "unique_mood"},
        {"ключ", "key"},
        {"ид", "id"},
        {"имя", "name"},
        {"координаты", "coordinates"},
        {"X координата", "x_coordinate"},
        {"Y координата", "y_coordinate"},
        {"дата создания", "creation_date"},
        {"настроение", "mood"},
        {"оружие", "weapon"},
        {"герой", "realHero"},
        {"есть зубочистка", "hasToothpick"},
        {"скорость", "impact_speed"},
        {"марка машины", "carName"},
        {"машина крутая", "carCool"},
        {"грусть", "sadness"},
        {"тоска", "longing"},
        {"апатия", "apathy"},
        {"злость", "rage"},
        {"топор", "axe"},
        {"нож", "knife"},
        {"винтовка", "rifle"},
        {"отправить", "send"},
        {"русский", "russian"},
        {"украинский", "ukrainian"},
        {"исландский", "icelandic"},
        {"испанский", "spanish"},
        {"результат", "result"},
        {"путь до скрипта", "script_path"},
        {"Сделано!", "done"},
        {" нет аккаунта?", "account_question"},

        {"error.script_recursion", "Вы не можете выполнять скрипты рекурсивно!"},
        {"error.noSuchFile", "Файл не найден"},
        {"error.serverIsDown", "Ошибка при подключении к серверу"},
        {"error.InvalidInputException", "Некорректные данные"},
        {"error.InvalidPassword", "Неверный пароль"},
        {"error.ImpactSpeed", "Скорость должна быть числом"},
        {"error.ResponseError", "При выполнении запроса возникла ошибка "},
        {"error.InvalidArguments", "Некорректные аргументы"},
        {"error.AccessError", "У вас нет доступа к изменению этого параметра"},
        {"error.EmptyName", "Имя не может быть пустым"},
        {"error.CoordinateFormat", "Координата должны быть числом"},
        {"error.MoodError", "Некорректное значение настроения"},
        {"error.CarCoolError", "carCool должно быть true или false"},
        {"error.HasToothpick", "hasToothpick должен быть true или false"},
        {"error.RealHero", "realHero должен быть true / 1 или false / 0"},
        {"error.WeaponType", "Некорректное значение оружия"},
        {"error.", ""},
        {"error.", ""},
        {"error.", ""},
        {"error.", ""}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

