create table public.cars (
                             id bigint primary key not null default nextval('cars_id_seq'::regclass),
                             brand character varying(255),
                             car_has_active boolean,
                             model character varying(255),
                             employee_id integer,
                             foreign key (employee_id) references public.users (id)
                                 match simple on update no action on delete no action
);

create table public.cards (
                              id integer primary key not null default nextval('cards_id_seq'::regclass),
                              number character varying(255)
);