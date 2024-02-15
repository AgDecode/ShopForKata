# ShopForKata

mini-Pet Project - CRUD приложение Магазин

Технологии, которые я использовал:

1. Spring
2. Spring MVC
3. Spring Boot
4. Spring Security(Шифрование паролей ✅, Зашита от CSRF атак✅)
5. Hibernate,PostgresQL(Зашита от SQL и HQL инъекции ✅)
6. Spring DATA JPA
7. AspectJ (AOP)
8. Spring Validator
9. Thymeleaf
10. Html, Css

Функционал :
AllUser(UnAuthorized):
1. Страницы регистрации и логина человека, возможность пройти регистрацию.
2. Основные страницы : adoutUs, Contacts.
3. Страницы магазина (при нажатии "положить в корзину" редирект на регистрацию).
User:
1. Страница Профиля и возможность изменения данных о себе.
2. Страница Корзины пользователя.
3. Возможность добавлять и удалять продукты из корзины.
Менеджеры :
1. Страница менеджмента продуктов с возможностями: удалять, добавлять, менять продукты.
2. Страница менеджмента людей с возможностями: изменять роль, удалять человека.



Проблемы, которые я решал :
1. Множество раз сталкивался и решал проблему циклических зависимостей посредством аннотации @Lazy или выносом метода в отдельный класс.
2. Весь проект был сделан на Spring 6.1 в котором большинство deprications методов были удалены - пришлось изучать новые решения.
3. Расширяемая и красивая реализация Header без дублирования кода - посредством AOP. 
