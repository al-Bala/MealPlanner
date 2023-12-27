db = db.getSiblingDB('testDatabase');

db.users.insertMany([
    {
        id: "#",
        role: "USER",
        username: "testUser",
        password: "password",
        email: "email@email.pl",
        user_preferences: {
            portions: 2,
            diet: "wege",
            disliked_products: [""]
        },
        user_recipes: [""],
        plan_history: [
            { day: new Date(2023,12,13), recipe: ObjectId('6577660abbac733a111c9421')},
            { day: new Date(2023,12,13), recipe: ObjectId('6577660abbac733a111c9424')}
        ],
    },
]);

db.recipes.insertMany([
    {
        name: "Kasza jaglana z warzywami",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "wegetariańska",
        ingredients: [
            { name: "kasza jaglana", amount: 200, unit: "g" },
            { name: "marchew", amount: 2, unit: "szt" },
            { name: "brokuły", amount: 150, unit: "g" },
            { name: "oliwa z oliwek", amount: 30, unit: "ml" }
        ],
        steps: ["Ugotuj kaszę", "Pokrój warzywa", "Smaż warzywa na oliwie", "Podawaj razem"]
    },
    {
        name: "Sałatka owocowa",
        portions: 3,
        prepare_time: 15,
        max_storage_time: 1,
        diet: "wegetariańska",
        ingredients: [
            { name: "jabłko", amount: 2, unit: "szt" },
            { name: "kiwi", amount: 3, unit: "szt" },
            { name: "truskawki", amount: 150, unit: "g" },
            { name: "miód", amount: 20, unit: "ml" }
        ],
        steps: ["Pokrój owoce", "Połącz z miodem", "Delikatnie wymieszaj", "Gotowe do podania"]
    },
    {
        name: "Jajecznica z warzywami",
        portions: 2,
        prepare_time: 15,
        max_storage_time: 1,
        diet: "wegetariańska",
        ingredients: [
            { name: "jajka", amount: 4, unit: "szt" },
            { name: "papryka", amount: 1, unit: "szt" },
            { name: "pomidor", amount: 2, unit: "szt" },
            { name: "cebula", amount: 1, unit: "szt" },
            { name: "oliwa z oliwek", amount: 20, unit: "ml" }
        ],
        steps: ["Ubij jajka", "Pokrój warzywa", "Smaż warzywa, dodaj jajka", "Podawaj gorące"]
    },
    {
        name: "Koktajl owocowy",
        portions: 1,
        prepare_time: 10,
        max_storage_time: 1,
        diet: "wegetariańska",
        ingredients: [
            { name: "banan", amount: 1, unit: "szt" },
            { name: "truskawki", amount: 100, unit: "g" },
            { name: "kiwi", amount: 1, unit: "szt" },
            { name: "sok pomarańczowy", amount: 150, unit: "ml" },
            { name: "jogurt naturalny", amount: 50, unit: "g" }
        ],
        steps: ["Włóż owoce do blendera", "Dodaj sok i jogurt", "Miksuj do uzyskania gładkiego koktajlu", "Gotowe do picia"]
    },
    {
        name: "Ryż z warzywami i kurczakiem",
        portions: 3,
        prepare_time: 25,
        max_storage_time: 2,
        diet: "mięsna",
        ingredients: [
            { name: "ryż", amount: 300, unit: "g" },
            { name: "kurczak", amount: 250, unit: "g" },
            { name: "marchew", amount: 2, unit: "szt" },
            { name: "brokuły", amount: 150, unit: "g" },
            { name: "sos sojowy", amount: 30, unit: "ml" }
        ],
        steps: ["Ugotuj ryż", "Pokrój kurczaka i warzywa", "Smaż kurczaka i warzywa, dodaj sos sojowy", "Podawaj razem z ryżem"]
    }
]);

db.products.insertMany([
    {
        name: "kasza jaglana",
        packing_measures: [
            { amount: 200, unit: "g" },
            { amount: 400, unit: "g" }
        ],
    },
    {
        name: "marchew",
        packing_measures: [
            { amount: 0, unit: "g" },
            { amount: 0, unit: "szt" }
        ],
    },
    {
        name: "brokuły",
        packing_measures: [
            { amount: 0, unit: "g" },
            { amount: 0, unit: "szt" }
        ],
    },
    {
        name: "oliwa z oliwek",
        packing_measures: [
            { amount: 50, unit: "ml" }
        ],
    }
]);



