
INSERT INTO public.classroom (id,class_number,people_capacity,id_college) VALUES (1,'A-12',10,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.classroom (id,class_number,people_capacity,id_college) VALUES (2,'B-11',20,2) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.classroom (id,class_number,people_capacity,id_college) VALUES (3,'A-10',30,3) ON CONFLICT (id) DO NOTHING;