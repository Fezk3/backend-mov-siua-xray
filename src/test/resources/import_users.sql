

INSERT INTO public.users (id, firstName, lastName, password, email, tokenExpired) VALUES (1, 'Victor', 'Segura', '$2a$10', 'segura@gmail.com', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.users (id, firstName, lastName, password, email, tokenExpired) VALUES (2, 'Diego', 'Espinoza', '#$%SDfds', 'asdad@gmail.com', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.users (id, firstName, lastName, password, email, tokenExpired) VALUES (3, 'Victor', 'Segura', '$2a$asd10', 'asdasdqe@gmail.com', True) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.users (id, firstName, lastName, password, email, tokenExpired) VALUES (4, 'Diego', 'Espinoza', '#$%SDfds', 'asdad@gmail.com', false) ON CONFLICT (id) DO NOTHING;