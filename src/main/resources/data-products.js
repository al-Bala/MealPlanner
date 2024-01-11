db.createCollection("products")

db.products.insertMany([
    {
        _id: ObjectId('658c088e98487458f640453b'),
        name: "kasza jaglana",
        packing_units: ["g","kg"],
        main_unit: "g",
        packing_measures: [400,1000]
    },
    {
        _id: ObjectId('658c088e98487458f640453c'),
        name: "marchew",
        packing_units: ["g","kg","szt"],
        main_unit: "g",
        packing_measures: [100]
    },
    {
        _id: ObjectId('658c088e98487458f640453d'),
        name: "brokuł",
        packing_units: ["g","kg","szt"],
        main_unit: "g",
        packing_measures: [150]
    },
    {
        _id: ObjectId('658c088e98487458f640453e'),
        name: "oliwa z oliwek",
        packing_units: ["ml","l"],
        main_unit: "ml",
        packing_measures: [50,250,1000]
    },
    {
        _id: ObjectId('6597dcbef4654a0007f29c1f'),
        name: "kasza gryczana",
        packing_measures:[
            {
                "amount":400,
                "unit":"g"
            }
        ]
    },
    {
        _id: ObjectId('659d8d69d6d8ec0007e14d76'),
        name: "jajko",
        packing_units: ["szt"],
        main_unit: "szt",
        packing_measures: [6,10]
    }
]);