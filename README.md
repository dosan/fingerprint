#  Timetra система учета времени

### Использованные библиотеки
для ознакомления документации пройдите по ссылке:

* [Документация Apache Maven](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/maven-plugin/)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [SourceAFIS Библиотека для обработки отпечатка пальца](https://sourceafis.machinezoo.com/)

 
### Config сервера
ip адрес где будет запускаться ваш сервис-`server.address=89.218.85.206` 

порт выделяемый для вашего сервиса- `server.port=5000`
 
### Config Баз данных
Ссылка для подключения баз данных -`spring.datasource.url=jdbc:mysql://localhost:3306/bio?useUnicode=true&characterEncoding=UTF-8`
 
Пользователь в базе данных -`spring.datasource.username=biodb`
 
Пароль пользователь в базе данных -`spring.datasource.password=ei7veeChu4bo`
 
Значение позволяющий создавать таблицы если в базе данных их не будет-`spring.jpa.generate-ddl=true`

## Описание REST APIs
В этом сервисе присутствуют такие роуты для взаимодействия с мобильным приложением 

URI|request|response|description
---|---|---|---
/upload/register|POST {file: MultipartFile, orgId: 1}| {id: 1, name:'Kanat', surname:'Yeleussiz',iin:'961117305102'}| Сверка отпечатков пользователя
/upload/save|POST {file: MultipartFile, name:'Kanat', surname:'Yeleussiz',iin:'961117305102'}|{body:'CountOfFingerprint'}| Сохранение отпечатки пальцев пользователя
/auth/login|POST {email:'kanatmg1@gmail.com', password:'password'}| {id:'1', name:'title',totalnumberseats: '300', status:'1'}| Вход в систему через планшет
/auth/save|POST {email:'kanatmg1@gmail.com', password:'password', org: 1} |{body:'title'}| Сохранение пароля для организации


## Исходный код
 [https://github.com/kanatmg/fingerprint/tree/second_prototype](https://github.com/kanatmg/fingerprint/tree/second_prototype)