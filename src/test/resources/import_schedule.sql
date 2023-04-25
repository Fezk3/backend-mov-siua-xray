

INSERT INTO public.schedule (id, courseName, date, startTime, endTime, IdUser, IdClassroom) VALUES (1, 'Math', '2020-01-01', '2020-01-01', '2020-01-01', 1, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.schedule (id, courseName, date, startTime, endTime, IdUser, IdClassroom) VALUES (2, 'Math', '2020-01-01', '2020-01-01', '2020-01-01', 2, 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.schedule (id, courseName, date, startTime, endTime, IdUser, IdClassroom) VALUES (3, 'Math', '2020-01-01', '2020-01-01', '2020-01-01', 3, 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.schedule (id, courseName, date, startTime, endTime, IdUser, IdClassroom) VALUES (4, 'Math', '2020-01-01', '2020-01-01', '2020-01-01', 4, 4) ON CONFLICT (id) DO NOTHING;