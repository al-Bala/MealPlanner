db.users.updateOne(
    { _id: ObjectId('658e7e82267fb7171492ffa5') },
    {
        $set: {
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
    }
);

db.users.updateOne(
    { _id: ObjectId('658e801c20709749de3faf9a') },
    {
        $set: {
            role: "ADMIN",
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
    }
);



