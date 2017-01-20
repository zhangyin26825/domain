server {
listen 80;
server_name ${domainName};
location / {
            index index.html;
            proxy_redirect off;
            proxy_set_header Host $host;
            proxy_set_header   X-Real-IP   $remote_addr;
            proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass  http://${projectName}/;
            error_page  404 403 500 503 502 = /4xx.html;
            access_log /mnt/log/youyu/nginx/${projectName}-access-all.log;
}
    access_log   /mnt/log/weijuju/nginx/${projectName}.log;
}
upstream ${projectName}
{
    ip_hash;
    server 127.0.0.1:${port?c};
}
