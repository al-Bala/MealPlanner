db.createCollection("users")

db.users.insertMany([
    {
        id: "#1",
        role: "USER",
        username: "user",
        password: "1234",
        email: "uuu@uu.u",
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
        id: "#2",
        role: "ADMIN",
        username: "admin",
        password: "1234",
        email: "aaa@aa.a",
        user_preferences: {
            portions: 2,
            diet: "mięsna",
            disliked_products: [""]
        },
        user_recipes: [""],
        plan_history: [
            { day: new Date(2023,12,5), recipe: ObjectId('6577660abbac733a111c9421')},
            { day: new Date(2023,12,7), recipe: ObjectId('6577660abbac733a111c9424')}
        ],
    },
]);



