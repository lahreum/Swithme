module.exports = {
  devServer: {
    https: {
      key: fs.readFileSync('j4b103.p.ssafy.io.key'),
      cert: fs.readFileSync('j4b103.p.ssafy.io.crt'),
      ca: fs.readFileSync('rootca.crt'),
    },
  },
  transpileDependencies: ['vuetify'],
};
