db.createCollection("users")

db.users.insertMany([
    {
        _id: ObjectId('658e7e82267fb7171492ffa5'),
        role: "USER",
        username: "user",
        email: "uuu@uu.u",
        password: "$2a$10$UTR/3trn8fi4E9neMx.pUOEMDJcImMT5DkeTZOf7vZxpacwkgriwa",
        user_preferences: {
            portions: 2,
            diet: "wegańska",
            disliked_products: [""]
        },
        user_recipes: [""],
        plan_history: [
            { day: new Date(2023,12,11), recipe: ObjectId('6577660abbac733a111c9421')},
            { day: new Date(2023,12,12), recipe: ObjectId('6577660abbac733a111c9424')}
        ],
    },
    {
        _id: ObjectId('658e801c20709749de3faf9a'),
        role: "ADMIN",
        username: "admin",
        email: "aaa@aaa.aa",
        password: "$2a$10$VIqdqaLOyt77Ro411EzmHuRDFCsLbX6CqR6M.P35CzaMzQj/a/kKG",
        user_preferences: {
            portions: 2,
            diet: "wegańska",
            disliked_products: [""]
        },
        user_recipes: [""],
        plan_history: [
            { day: new Date(2023,12,11), recipe: ObjectId('6577660abbac733a111c9421')},
            { day: new Date(2023,12,12), recipe: ObjectId('6577660abbac733a111c9424')}
        ],
    }
]);
