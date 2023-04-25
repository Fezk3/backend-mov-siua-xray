
INSERT INTO public.assetType(id,description) VALUES (1,'Estatico') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.assetType(id,description) VALUES (2,'Dinamico') ON CONFLICT (id) DO NOTHING;