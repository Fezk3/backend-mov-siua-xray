
INSERT INTO public.assets (id,name,quantity,IdAssetType,IdClassroom)  VALUES (1,'Control pizarra',1,1,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.assets (id,name,quantity,IdAssetType,IdClassroom)  VALUES (2,'Laptops',30,2,1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.assets (id,name,quantity,IdAssetType,IdClassroom)  VALUES (3,'Control del aire',1,1,2) ON CONFLICT (id) DO NOTHING;