// from https://github.com/TarasovVP/Android-Architecure-Templates/blob/kmp_implementation/webApp/webpack.config.d/wasm.js
// the error shows "expected magic word 00 61 73 6d, found 3c 21 44 4f", but it really means the wasm file is missing
// so we add the wasm file manually
const CopyWebpackPlugin = require('copy-webpack-plugin');
config.plugins.push(
    new CopyWebpackPlugin({
        patterns: [
            '../../node_modules/sql.js/dist/sql-wasm.wasm'
        ]
    })
);