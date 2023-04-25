

INSERT INTO public.college (id, name) VALUES (1, 'College 1') ON CONFLICT (id) DO NOTHING;;
INSERT INTO public.college (id, name) VALUES (2, 'College 2') ON CONFLICT (id) DO NOTHING;;
INSERT INTO public.college (id, name) VALUES (3, 'College 3') ON CONFLICT (id) DO NOTHING;;
INSERT INTO public.college (id, name) VALUES (4, 'College 4') ON CONFLICT (id) DO NOTHING;;