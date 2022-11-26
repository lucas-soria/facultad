# pull official base image
FROM node:16.13.0

# set working directory
WORKDIR /carrito-front

# add `/app/node_modules/.bin` to $PATH
ENV PATH /carrito-front/node_modules/.bin:$PATH

# add app
COPY . ./

# install app dependencies
RUN npm install -g npm@8.1.3
RUN npm install --silent
RUN npm install react-scripts@4.0.3 -g --silent
RUN npm run build
RUN npm install -g serve

# start app
# CMD ["npm", "start"]
CMD ["serve", "-s", "build"]

