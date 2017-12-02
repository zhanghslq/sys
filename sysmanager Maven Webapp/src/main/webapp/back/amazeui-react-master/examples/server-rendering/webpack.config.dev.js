'use strict';

import path from 'path';
import webpack  from 'webpack';

export default {
  devtool: 'eval-source-map',

  entry: [
    // 'webpack/hot/dev-server',
    // 'webpack-hot-middleware/client?reload=true',
    __dirname + '/client/entry.js'
  ],

  output: {
    path: __dirname + '/public/js/',
    filename: 'bundle.js',
    publicPath: '/js/'
  },

  resolve: {
    alias: {
      react: __dirname + '/node_modules/react',
      'react-dom': __dirname + '/node_modules/react-dom'
    }
  },

  plugins: [
    new webpack.optimize.OccurenceOrderPlugin(),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin()
  ],

  resolveLoader: {
    root: path.join(__dirname, '../../node_modules')
  },

  module: {
    loaders: [
      {
        test: /\.js$/,
        loaders: ['babel'],
        exclude: /node_modules/,
        include: [
          path.join(__dirname, '../../src'),
          path.join(__dirname, 'client'),
          path.join(__dirname, 'shared'),
        ]
      }
    ]
  }
};
