const path = require("path")
const MiniCssExtractPlugin = require("mini-css-extract-plugin")
const webpack = require("webpack")

const presets = [
  ["@babel/preset-env", { useBuiltIns: "usage", corejs: 3 }],
  ["@babel/preset-react", { runtime: "automatic" }],
]

module.exports = {
  mode: "development",
  entry: {
  },
  output: {
    path: path.resolve(__dirname, "public"),
    publicPath: "./public",
    filename: "[name].js",
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        use: {
          loader: "babel-loader",
          options: { presets },
        },
        exclude: /node_modules/,
      },
      {
        test: /\.jsx$/,
        use: {
          loader: "babel-loader",
          options: { presets },
        },
        exclude: /node_modules/,
      },
      {
        test: /\.css$/i,
        use: [MiniCssExtractPlugin.loader, "css-loader"],
      },
    ],
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: "[name].css",
      chunkFilename: "[id].css",
    }),
  ],
}
