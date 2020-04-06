db.createUser(
    {
        user: "user-app",
        pwd: "temporal",
        roles: [
            {
                role: "readWrite",
                db: "apirest"
            }
        ]
    }
);