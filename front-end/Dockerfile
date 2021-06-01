FROM nginx:alpine
ADD nginx.conf /etc/nginx/conf.d/default.conf
ADD build /var/www/html
EXPOSE 3000 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]