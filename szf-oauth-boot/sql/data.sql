-- 密码：123456
INSERT INTO szf_oauth.oauth_client_details(client_id, client_secret, scope, authorized_grant_types)
VALUES ('szf-system', '$2a$10$YqoOy2rzC4zrnLKKD.tRcejG.RRauDWvt/3LUG/4sPEmtDy1fpykC', 'all,read,write',
        'authorization_code,password,refresh_token');