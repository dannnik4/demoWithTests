alter table users drop id_deleted;

alter table users add if not exists is_deleted boolean;