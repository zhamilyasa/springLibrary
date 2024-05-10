
     CREATE SCHEMA IF NOT EXISTS public;
--
     create sequence hibernate_sequence start 1 increment 1;
-- -- -- -- Создание последовательности для идентификатора пользователей
 CREATE SEQUENCE public.users_seq START 1;
 -- -- Создание таблицы пользователей в схеме public
 CREATE TABLE public.users (
                        id BIGSERIAL PRIMARY KEY ,
                        name VARCHAR(20000),
                        username VARCHAR(255) UNIQUE,
                        password VARCHAR(255),
                        email VARCHAR(255)
 );

-- -- Создание таблицы ролей пользователей
 CREATE TABLE public.users_roles (
                              user_id BIGSERIAL,
                              role VARCHAR(50),
                             FOREIGN KEY (user_id) REFERENCES public.users(id)
 );
