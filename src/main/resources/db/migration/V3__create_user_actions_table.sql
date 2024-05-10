    CREATE TABLE public.user_actions (
                                  id SERIAL PRIMARY KEY,
                                  user_id BIGINT NOT NULL,
                                  book_id BIGINT NOT NULL,
                                  action_type VARCHAR(255) NOT NULL,
                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                  FOREIGN KEY (book_id) REFERENCES book(id)
    );
