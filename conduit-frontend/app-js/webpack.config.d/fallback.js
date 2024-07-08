// this is needed otherwise the sqldelight won't properly initialized
// similar issue see: https://github.com/joreilly/PeopleInSpace/issues/49
config.resolve = {
    fallback: {
        fs: false,
        path: false,
        crypto: false,
    },
};