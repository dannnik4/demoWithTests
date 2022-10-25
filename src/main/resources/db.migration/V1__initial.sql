create table public.users (
                              id integer primary key not null default nextval('users_id_seq1'::regclass),
                              country character varying(255),
                              email character varying(255),
                              name character varying(255),
                              address character varying,
                              phone character varying,
                              employee_id integer,
                              id_deleted boolean,
                              gender character varying,
                              foreign key (employee_id) references public.cards (id)
                                  match simple on update no action on delete no action
);