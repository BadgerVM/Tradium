FROM pivotalpa/angular-cli as angular
WORKDIR /app

COPY ./angular /app
RUN npm install &&  ng build --env=prod --base-href /angular/
#--target=production --environment=prod
EXPOSE 4200
CMD ["ng", "serve"]