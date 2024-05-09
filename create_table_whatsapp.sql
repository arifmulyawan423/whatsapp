-- Table: public.user_profile

-- DROP TABLE IF EXISTS public.user_profile;

CREATE TABLE IF NOT EXISTS public.user_profile
(
    user_id uuid NOT NULL,
    user_name character varying COLLATE pg_catalog."default",
    user_status character varying COLLATE pg_catalog."default",
    phone_number character varying COLLATE pg_catalog."default",
    CONSTRAINT user_profile_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_profile
    OWNER to postgres;

-- Table: public.user_chat_room

-- DROP TABLE IF EXISTS public.user_chat_room;

CREATE TABLE IF NOT EXISTS public.user_chat_room
(
    chat_room_id uuid NOT NULL,
    user_id uuid NOT NULL,
    friend_user_id uuid NOT NULL,
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_chat_room_pkey PRIMARY KEY (chat_room_id),
    CONSTRAINT fk_friend_user_id FOREIGN KEY (friend_user_id)
        REFERENCES public.user_profile (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.user_profile (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_chat_room
    OWNER to postgres;

-- Table: public.user_message

-- DROP TABLE IF EXISTS public.user_message;

CREATE TABLE IF NOT EXISTS public.user_message
(
    user_message_id uuid NOT NULL,
    user_chat_room_id uuid NOT NULL,
    message character varying COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    file_name character varying COLLATE pg_catalog."default",
    file_url character varying COLLATE pg_catalog."default",
    CONSTRAINT user_message_pkey PRIMARY KEY (user_message_id),
    CONSTRAINT fk_user_chat_room_id FOREIGN KEY (user_chat_room_id)
        REFERENCES public.user_chat_room (chat_room_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_message
    OWNER to postgres;

-- Table: public.user_response_message

-- DROP TABLE IF EXISTS public.user_response_message;

CREATE TABLE IF NOT EXISTS public.user_response_message
(
    user_response_message_id uuid NOT NULL,
    user_message_id uuid NOT NULL,
    emoji_code character varying COLLATE pg_catalog."default" NOT NULL,
    response_user_id uuid NOT NULL,
    CONSTRAINT user_response_message_pkey PRIMARY KEY (user_response_message_id),
    CONSTRAINT fk_response_user_id FOREIGN KEY (response_user_id)
        REFERENCES public.user_profile (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_user_message_id FOREIGN KEY (user_message_id)
        REFERENCES public.user_message (user_message_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_response_message
    OWNER to postgres;