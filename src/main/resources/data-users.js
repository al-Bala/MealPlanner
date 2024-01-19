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
            {
                day: ISODate('2024-01-18T23:00:00.000Z'),
                recipe: {
                    _id: ObjectId('659fb86d481c99322d2d2abe'),
                    name: 'Kasza jaglana z warzywami',
                    portions: 2,
                    prepare_time: 30,
                    diet: 'wegetariańska',
                    ingredients: [
                        {
                            name: 'kasza jaglana',
                            amount: 200,
                            unit: 'g'
                        },
                        {
                            name: 'marchew',
                            amount: 2,
                            unit: 'szt'
                        },
                        {
                            name: 'brokuł',
                            amount: 150,
                            unit: 'g'
                        },
                        {
                            name: 'oliwa z oliwek',
                            amount: 30,
                            unit: 'ml'
                        }
                    ],
                    steps: [
                        'Ugotuj kaszę',
                        'Pokrój warzywa',
                        'Smaż warzywa na oliwie',
                        'Podawaj razem'
                    ]
                }
            }
        ],
        favorites: []
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
