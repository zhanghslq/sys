'use strict';

var path = require('path');
var fs = require('fs');

// For instructions about this file refer to
// webpack and webpack-hot-middleware documentation
var webpack = require('webpack');
var autoprefixer = require('autoprefixer');

var entryBase = [
  'webpack/hot/dev-server',
  'webpack-hot-middleware/client?reload=true'
];
var entries = fs.readdirSync(__dirname).reduce(function(entries, dir) {
  if (fs.statSync(path.join(__dirname, dir)).isDirectory() &&
    dir !== 'node_modules') {
    var entryPath = path.join(__dirname, dir, 'app.js');

    if (fs.existsSync(entryPath)) {
      entries[dir] = entryBase.concat(entryPath);
    }
  }

  return entries;
}, {});

module.exports = {
  devtool: 'eval',
  // context: path.join(__dirname, 'app', 'js'),

  entry: entries,

  output: {
    path: __dirname + '/__build__',
    filename: '[name].bundle.js',
    chunkFilename: '[id].chunk.js',
    publicPath: '/__build__/'
  },

  resolve: {
    alias: {
      'amazeui-react': path.join(__dirname, '../src/AMUIReact')
    }
  },

  plugins: [
    new webpack.optimize.OccurenceOrderPlugin(),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin()
  ],

  resolveLoader: {
    root: path.join(__dirname, '../node_modules')
  },

  module: {
    loaders: [
      {
        test: /\.js$/,
        loaders: ['react-hot', 'babel'],
        include: [
          path.join(__dirname, '../src'),
          path.join(__dirname, 'admin')
        ]
      },
      {
        test: /\.less$/,
        loaders: [
          'style',
          'css',
          'postcss',
          'less'
        ],
        include: [
          path.join(__dirname, 'admin')
        ]
      }
    ]
  },

  postcss: [autoprefixer({browsers: ['> 1%', 'last 2 versions']})]
};
