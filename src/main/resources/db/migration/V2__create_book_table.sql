CREATE TABLE public.book
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(20000),
    author         VARCHAR(20000),
    category       VARCHAR(20000),
    year           VARCHAR(20000),
    description    VARCHAR(20000),
    views          VARCHAR(20000),
    cover_url      VARCHAR(20000),
    isbn           VARCHAR(20000),
    average_rating VARCHAR(20000),
    page_count     VARCHAR(20000)
);