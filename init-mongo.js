db.createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "planner"
            }
        ]
    }
)

db.users.insertMany([
    {
        _id: ObjectId('658e7e82267fb7171492ffa5'),
        role: "ADMIN",
        username: "admin",
        email: "aaa@aaa.aa",
        password: "$2a$10$UTR/3trn8fi4E9neMx.pUOEMDJcImMT5DkeTZOf7vZxpacwkgriwa",
        plan_history: [],
        favorites: []
    },
]);

db.products.insertMany([
    {
        name: "cebula",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            100
        ]
    },
    {
        name: "marchewka",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            100
        ]
    },
    {
        name: "pomidor",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "bulion",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: []
    },
    {
        name: "papryka czerwona",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "cukinia",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "boczniaki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            200,
            250
        ]
    },
    {
        name: "sos sojowy",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            150,
            250
        ]
    },
    {
        name: "ziemniaki",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "białe szparagi",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            60
        ]
    },
    {
        name: "oliwa",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            250,
            500,
            750,
            1000
        ]
    },
    {
        name: "musztarda francuska",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            175,
            180,
            185,
            270
        ]
    },
    {
        name: "cukier",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            1000
        ]
    },
    {
        name: "olej",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            500,
            1000
        ]
    },
    {
        name: "mleko ryżowe",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            1000
        ]
    },
    {
        name: "mąka ziemniaczana",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            500,
            1000
        ]
    },
    {
        name: "otręby pszenne",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            1000
        ]
    },
    {
        name: "kalarepa",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            300
        ]
    },
    {
        name: "rzodkiewka",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            10
        ]
    },
    {
        name: "pietruszka",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            40
        ]
    },
    {
        name: "por",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            160
        ]
    },
    {
        name: "natka pietruszki",
        packing_units: [
            "szt"
        ],
        main_unit: "szt",
        packing_measures: [
            1
        ]
    },
    {
        name: "woda",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: []
    },
    {
        name: "bulion warzywny",
        packing_units: [
            "kostki"
        ],
        main_unit: "kostki",
        packing_measures: [
            6,
            10
        ]
    },
    {
        name: "makaron",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            250,
            500
        ]
    },
    {
        name: "bazylia",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            30
        ]
    },
    {
        name: "tofu",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            180,
            200,
            300,
            400
        ]
    },
    {
        name: "fasolka szparagowa",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "papryczka chilli",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            10
        ]
    },
    {
        name: "napój kokosowy",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            400,
            1000
        ]
    },
    {
        name: "wiórki kokosowe",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            100,
            125,
            200,
            400
        ]
    },
    {
        name: "bakłażan",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            240
        ]
    },
    {
        name: "jajka",
        packing_units: [
            "szt"
        ],
        main_unit: "szt",
        packing_measures: [
            6,
            10
        ]
    },
    {
        name: "kasza jaglana",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            350,
            400,
            500
        ]
    },
    {
        name: "rodzynki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            200,
            1000
        ]
    },
    {
        name: "mąka pszenna",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            1000
        ]
    },
    {
        name: "olej rzepakowy",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            500,
            1000
        ]
    },
    {
        name: "ryż",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            400,
            500
        ]
    },
    {
        name: "pasta curry",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            114,
            165,
            190,
            220
        ]
    },
    {
        name: "kolendra",
        packing_units: [
            "szt"
        ],
        main_unit: "szt",
        packing_measures: [
            1
        ]
    },
    {
        name: "cytryna",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            130
        ]
    },
    {
        name: "zielony groszek",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            400,
            450,
            600
        ]
    },
    {
        name: "włoszczyzna",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            500
        ]
    },
    {
        name: "fasola",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            400,
            425
        ]
    },
    {
        name: "pieczarki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            250,
            500
        ]
    },
    {
        name: "papryka",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "kasza gryczana",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            400,
            500
        ]
    },
    {
        name: "kasza jęczmienna",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            400,
            500
        ]
    },
    {
        name: "śmietana",
        packing_units: [
            "g",
            "kg",
            "łyżka",
            "łyżeczka"
        ],
        main_unit: "g",
        packing_measures: [
            200,
            400
        ]
    },
    {
        name: "pierś z indyka",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "ananas z puszki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            435,
            565,
            580
        ]
    },
    {
        name: "ser żółty",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "pomidory z puszki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            260,
            400
        ]
    },
    {
        name: "schab",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "papryka żółta",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "kukurydza z puszki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            170,
            340,
            400
        ]
    },
    {
        name: "czosnek",
        packing_units: [
            "ząbek"
        ],
        main_unit: "ząbek",
        packing_measures: [
            12
        ]
    },
    {
        name: "pierś z kurczaka",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "placki tortilli",
        packing_units: [
            "szt"
        ],
        main_unit: "szt",
        packing_measures: [
            4,
            5,
            8
        ]
    },
    {
        name: "papryka zielona",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            180
        ]
    },
    {
        name: "czerwona cebula",
        packing_units: [
            "g",
            "kg",
            "szt"
        ],
        main_unit: "g",
        packing_measures: [
            100
        ]
    },
    {
        name: "kurki",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "ziarna słonecznika",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: [
            80,
            100
        ]
    },
    {
        name: "dynia",
        packing_units: [
            "g",
            "kg"
        ],
        main_unit: "g",
        packing_measures: []
    },
    {
        name: "wino",
        packing_units: [
            "ml",
            "l"
        ],
        main_unit: "ml",
        packing_measures: [
            500
        ]
    }
]);

db.recipes.insertMany([
    {
        _id: ObjectId("5d13a11df74ec0f526fe9e44"),
        name: "Pomidorowa z kalafiorowym ryżem",
        portions: 4,
        prepare_time: 60,
        max_storage_time: 2,
        diet: "bezglutenowa",
        ingredients: [
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "marchewka",
                amount: 1,
                unit: "szt"
            },
            {
                name: "pomidor",
                amount: 2,
                unit: "kg"
            },
            {
                name: "bulion",
                amount: 300,
                unit: "ml"
            }
        ],
        steps: ["Cebulę pokroić na grube półksiężyce', 'Marchew obrać i pokroić w plasterki', 'Na dnie dużego garnka rozgrzać olej, dodać pokrojone warzywa i smażyć na niedużym ogniu przez 4 – 6 minut, czyli do czasu, aż cebulka będzie złota i miękka', 'W tym czasie z pomidorów wykroić szypułki i pokroić je na ćwiartki', 'Nie ma potrzeby obierać ani wyjmować galaretkowatego wnętrza', 'Pokrojone pomidory wrzucić do garnka z podsmażoną cebulą i marchwią', 'Dodać cynamon, sól, czarny pieprz i bulion', 'Koperek związać sznurkiem i również dorzucić do garnka, na koniec garnek przykryć i gotować przez przynajmniej 45 minut', 'Po upływie tego czasu wyjąć koperek i wyrzucić', 'Zupę przelać do blendera i zblendować', 'Doprawić do smaku solą i pieprzem, podawać ze startym kalafiorem i koperkiem"]
    },
    {
        _id: ObjectId("f5924475000d5a6bb8001dd7"),
        name: "Leczo z boczniakami",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "bezglutenowa",
        ingredients: [
            {
                name: "cebula",
                amount: 2,
                unit: "szt"
            },
            {
                name: "papryka czerwona",
                amount: 3,
                unit: "szt"
            },
            {
                name: "cukinia",
                amount: 2,
                unit: "szt"
            },
            {
                name: "pomidor",
                amount: 1,
                unit: "kg"
            },
            {
                name: "boczniaki",
                amount: 250,
                unit: "g"
            },
            {
                name: "sos sojowy",
                amount: 20,
                unit: "ml"
            }
        ],
        steps: [ "Cebule pokroić w półksiężyce, jeśli są bardzo duże przekroić jeszcze raz na pół", "Na dnie dużego garnka rozgrzać olej i dodać cebulę, smażyć na średnim ogniu przez 2 – 3 minuty", "W tym czasie papryki przekroić na pół, wydrążyć gniazda nasienne i pokroić w grube paski", "Tak pokrojoną paprykę dorzucić do cebuli razem ze wszystkimi rodzajami sproszkowanej papryki, zwiększyć ogień i smażyć przez 4 – 6 minut", "W międzyczasie cukinię pokroić w grubą kostkę, a boczniaki pokroić wzdłuż blaszek na mniejsze kawałki", "Do podsmażonej papryki dodać cukinie, krojone pomidory, sporą szczyptę soli oraz pieprzu i przykryć garnek", "Gotować pod przykryciem przez 10 minut", "Na patelni rozgrzać niedużą ilość oleju", "Wrzucić boczniaki, nie mieszać i smażyć przez minutę, aby się zezłociły", "Obrócić smażyć kolejną minutę, po czym dodać sos sojowy, cukier oraz czarny pieprz", "Intensywnie zamieszać, aby boczniaki równo pokryły się sosem sojowym, a gdy sos odparuje zdjąć patelnią z ognia", "Kiedy warzywa w leczo będą miękkie dorzucić do nich usmażone boczniaki, dokładnie wymieszać i gotować jeszcze minutę", "Podawać z ulubioną kaszą lub pieczywem", "Bardzo dobrze smakuje następnego dnia" ]
    },
    {
        _id: ObjectId("1583bc073d5889aabc9791cf"),
        name: "Pieczone białe szparagi z ziemniakami",
        portions: 4,
        prepare_time: 45,
        max_storage_time: 2,
        diet: "bezglutenowa",
        ingredients: [
            {
                name: "ziemniaki",
                amount: 4,
                unit: "szt"
            },
            {
                name: "białe szparagi",
                amount: 4,
                unit: "szt"
            },
            {
                name: "oliwa",
                amount: 20,
                unit: "ml"
            },
            {
                name: "cytryna",
                amount: 0.5,
                unit: "szt"
            },
            {
                name: "musztarda francuska",
                amount: 25,
                unit: "ml"
            }
        ],
        steps: [ "Piekarnik rozgrzać do 200 stopni", "W tym czasie ziemniaki obrać i pokroić na ósemki", "Szparagom odciąć zdrewniałe końcówki, obrać i pokroić na kawałki długości około 4 – 5 cm", "Ziemniaki wsypać do miski, dodać olej, sól i dokładnie je natrzeć", "Wysypać na blachę wyłożoną papierem do pieczenia i piec przez 10 – 12 minut", "W międzyczasie do tej samej miski włożyć szparagi i również natrzeć olejem i solą", "Kiedy ziemniaki zaczną się rumienić blachę wysunąć i dodać szparagi, dokładnie wymieszać i piec kolejne 10 minut", "W czasie gdy szparagi się pieką posiekać koper, a w szklance wymieszać wszystkie składniki sosu", "Gotowe szparagi i ziemniaki polać sosem, posypać dużą ilością kopru i podawać na stół", "Można zjeść bez żadnych dodatków lub z surówką z młodej kapusty, dobre na gorąco i na zimno" ]
    },
    {
        _id: ObjectId("079171d33c0a46732222438e"),
        name: "Wegańskie naleśniki bez jajek",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "wegańska",
        ingredients: [
            {
                name: "cukier",
                amount: 10,
                unit: "g"
            },
            {
                name: "olej",
                amount: 20,
                unit: "ml"
            },
            {
                name: "mleko ryżowe",
                amount: 400,
                unit: "ml"
            },
            {
                name: "mąka ziemniaczana",
                amount: 30,
                unit: "g"
            },
            {
                name: "otręby pszenne",
                amount: 150,
                unit: "g"
            }
        ],
        steps: [ "W kielichu miksera połączyć wszystkie dodatki: mąkę gryczaną, ziemniaczaną, otręby, mleko, olej i cukier", "Wszystko razem dokładnie zmiksuj na gładką masę", "Ciasto odstaw na 20 minut na bok", "Po tym czasie ciasto wylewaj małymi porcjami na posmarowaną tłuszczem patelnię teflonową", "Smaż naleśniki z obu stron na złoty kolor", "Układaj jeden na drugim aby nieco zaparowały i zrobiły się bardziej elastyczne", "Gotowe naleśniki podawaj z dowolnym farszem" ]
    },
    {
        _id: ObjectId("c206ef298986bea1c2286723"),
        name: "Zupa z kalarepy i rzodkiewek",
        portions: 6,
        prepare_time: 60,
        max_storage_time: 2,
        diet: "wegańska",
        ingredients: [
            {
                name: "kalarepa",
                amount: 2,
                unit: "szt"
            },
            {
                name: "rzodkiewka",
                amount: 6,
                unit: "szt"
            },
            {
                name: "ziemniaki",
                amount: 2,
                unit: "szt"
            },
            {
                name: "marchewka",
                amount: 1,
                unit: "szt"
            },
            {
                name: "pietruszka",
                amount: 1,
                unit: "szt"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "por",
                amount: 1,
                unit: "szt"
            },
            {
                name: "natka pietruszki",
                amount: 1,
                unit: "szt"
            },
            {
                name: "woda",
                amount: 900,
                unit: "ml"
            },
            {
                name: "bulion warzywny",
                amount: 2,
                unit: "kostki"
            }
        ],
        steps: [ "Umytą i obraną marchewkę, pietruszkę, selera, pół pora (tylko białą część) pokrój, zioła posiekaj", ", Wrzuć warzywa do 1,7 litra wody", "Dodaj obraną, pokrojoną jedną kalarepkę i ziemniaki pokrojone w kostkę oraz kostki Knorr", "Gotuj do miękkości warzyw", "Pod koniec gotowania wrzuć połowę natki i koperku", "Dopraw pieprzem, a następnie zmiksuj", ", Pozostałą część pora pokrój w cienkie plasterki, cebulę pokrój w piórka, a kalarepkę w słupki", "Rzodkiewkę pokrój w plasterki", "Warzywa podsmaż na oliwie przez 2 minuty", "Następnie dodaj pokrojoną w plasterki rzodkiewkę i smaż jeszcze 2 minuty", "Warzywa z patelni dodaj do zupy i zagotuj", "Zupę z kalarepy i rzodkiewek podaj posypaną natką i koperkiem zaraz po przygotowaniu" ]
    },
    {
        _id: ObjectId("a0a6a06c5745ba77dc819d8f"),
        name: "Penne w pomidorowym sosie",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 3,
        diet: "wegańska",
        ingredients: [
            {
                name: "makaron",
                amount: 300,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "cukinia",
                amount: 1,
                unit: "szt"
            },
            {
                name: "marchewka",
                amount: 1,
                unit: "szt"
            },
            {
                name: "pomidory z puszki",
                amount: 1,
                unit: "szt"
            },
            {
                name: "oliwa",
                amount: 50,
                unit: "ml"
            },
            {
                name: "bazylia",
                amount: 10,
                unit: "g"
            }
        ],
        steps: [ "Makaron ugotuj na sposób al dente, według zaleceń producenta", "Cebulę pokrój w półplastry, marchew obierz, cukinię i marchew pokrój w cienkie plastry", "W garnku na rozgrzanej oliwie podsmaż warzywa, zalej je pomidorami z puszki, dodaj Knorr Naturalnie smaczne - Spaghetti Bolognese", "Całość dokładnie wymieszaj i zagotuj", "Sos dopraw do smaku suszonym oregano i garścią posiekanej bazylii" ]
    },
    {
        _id: ObjectId("f0a890fc917ef45beaad5f49"),
        name: "Tofu w curry",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "wegańska",
        ingredients: [
            {
                name: "tofu",
                amount: 200,
                unit: "g"
            },
            {
                name: "fasolka szparagowa",
                amount: 50,
                unit: "g"
            },
            {
                name: "papryczka chilli",
                amount: 1,
                unit: "szt"
            },
            {
                name: "napój kokosowy",
                amount: 300,
                unit: "ml"
            },
            {
                name: "olej",
                amount: 20,
                unit: "ml"
            },
            {
                name: "wiórki kokosowe",
                amount: 50,
                unit: "g"
            }
        ],
        steps: [ "Tofu wyjmij z opakowania, odcedź, delikatnie odciśnij na ręczniku papierowym, a następnie pokrój w dużą kostkę i skrop olejem", "W osobnym garnku ugotuj ryż zgodnie z instrukcją podaną na opakowaniu", "Na dobrze rozgrzanej patelni ułóż kostki tofu i smaż krótko z każdej strony na złoty kolor", "Odstaw na bok (możesz ułożyć na kawałku ręcznika kuchennego dla odcieknięcia), W suchym rondlu podpraż wiórki kokosowe, często mieszając, żeby ich nie przypalić", "Gdy nabiorą koloru, dodaj napój kokosowy", "Wszystkie składniki dobrze wymieszaj, doprowadzając do wrzenia", "Następnie dodaj tofu oraz umytą i pozbawioną ogonków fasolkę, delikatnie zamieszaj i gotuj kolejne 10 minut", "Gotową potrawę posyp pokrojonym w cieniutkie plasterki chili", "Tofu w sosie curry podawaj z ryżem" ]
    },
    {
        _id: ObjectId("19b0653e0b3922f97918c2b3"),
        name: "Ratatouille",
        portions: 4,
        prepare_time: 60,
        max_storage_time: 2,
        diet: "wegańska",
        ingredients: [
            {
                name: "bakłażan",
                amount: 1,
                unit: "szt"
            },
            {
                name: "cukinia",
                amount: 2,
                unit: "szt"
            },
            {
                name: "cebula",
                amount: 3,
                unit: "szt"
            },
            {
                name: "pomidor",
                amount: 4,
                unit: "szt"
            },
            {
                name: "papryka czerwona",
                amount: 4,
                unit: "szt"
            },
            {
                name: "papryka zielona",
                amount: 1,
                unit: "szt"
            },
            {
                name: "oliwa",
                amount: 50,
                unit: "ml"
            }
        ],
        steps: [ "Bakłażana, cukinię, cebule i papryki pokrój w grubą kostkę", "Warzywa pomieszaj z oliwą, rozłóż na tacy wyłożonej pergaminem", "Wstaw na 30-40 minut do nagrzanego do 190° C piekarnika", "Upieczone warzywa przełóż do odpowiedniego garnka, dodaj pokrojone w kostkę pomidory, liść laurowy, posiekany tymianek i czosnek, wsyp Knorr Naturalnie Smaczne i dokładnie wymieszaj go z warzywami", "Całość gotuj jeszcze 5-10 minut, aż sos zrobi się gęsty" ]
    },
    {
        _id: ObjectId("a70d82508415a876bad3c58a"),
        name: "Kotlety z marchewki",
        portions: 5,
        prepare_time: 45,
        max_storage_time: 2,
        diet: "wegetariańska",
        ingredients: [
            {
                name: "marchewka",
                amount: 7,
                unit: "szt"
            },
            {
                name: "jajka",
                amount: 1,
                unit: "szt"
            },
            {
                name: "kasza jaglana",
                amount: 100,
                unit: "g"
            },
            {
                name: "natka pietruszki",
                amount: 1,
                unit: "szt"
            },
            {
                name: "rodzynki",
                amount: 50,
                unit: "g"
            },
            {
                name: "mąka pszenna",
                amount: 100,
                unit: "g"
            },
            {
                name: "olej rzepakowy",
                amount: 100,
                unit: "ml"
            }
        ],
        steps: [ "Marchew zetrzyj na tarce, posiekaj natkę pietruszki, ugotuj do miękkości a następnie wystudź kaszę jaglaną", "W misce pomieszaj kaszę, marchew, posiekaną natkę, siemię lniane rodzynki , jajko , mąki oraz przyprawy", "Z masy uformuj kotleciki , smaż je na rozgrzanym oleju po 1,5 minuty z każdej strony" ]
    },
    {
        _id: ObjectId("8b7bd51f89a6116d8bdd00cd"),
        name: "Ryż z groszkiem",
        portions: 4,
        prepare_time: 45,
        max_storage_time: 2,
        diet: "wegetariańska",
        ingredients: [
            {
                name: "ryż",
                amount: 200,
                unit: "g"
            },
            {
                name: "pasta curry",
                amount: 50,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 2,
                unit: "szt"
            },
            {
                name: "marchewka",
                amount: 1,
                unit: "szt"
            },
            {
                name: "kolendra",
                amount: 1,
                unit: "szt"
            },
            {
                name: "cytryna",
                amount: 1,
                unit: "szt"
            },
            {
                name: "bulion",
                amount: 500,
                unit: "ml"
            },
            {
                name: "olej",
                amount: 50,
                unit: "ml"
            },
            {
                name: "zielony groszek",
                amount: 80,
                unit: "g"
            }
        ],
        steps: [ "Ryż wypłucz pod bieżącą wodą", "Następnie odstaw, aby cała woda odciekła", "W dużym garnku rozgrzej połowę oleju i wsyp posiekaną cebulę", "Następnie dodaj ryż i całość podsmaż, aż ryż zrobi się lekko szklisty", "Wtedy wlej bulion tak, aby przykrył powierzchnię ryżu warstwą około 1,5 centymetra", "Dodaj szafran i pastę curry", "Gotuj powoli, aż ryż wchłonie prawie cały płyn", "Wtedy przykryj ryż szczelnie folią aluminiową i odstaw garnek na 10 minut", "Następnie odkryj ryż i delikatnie przemieszaj, aby się nie posklejał", "Odstaw ryż do ostygnięcia mieszając od czasu do czasu", "Na patelni rozgrzej pozostały olej i smaż marchewkę pokrojoną w drobną kostkę", "Jak tylko się zrumieni dodaj zielony groszek oraz ryż", "Wlej płyn na patelnię z ryżem", "Dodaj sok z cytryny oraz posiekaną kolendrę", "Podgrzewaj całość, aż wszystkie składniki dobrze się połączą", "Natychmiast podawaj" ]
    },
    {
        _id: ObjectId("7de3265af457ff99642c3f0a"),
        name: "Pierożki gyoza",
        portions: 4,
        prepare_time: 45,
        max_storage_time: 2,
        diet: "wegetariańska",
        ingredients: [
            {
                name: "włoszczyzna",
                amount: 1,
                unit: "szt"
            },
            {
                name: "mąka pszenna",
                amount: 900,
                unit: "g"
            },
            {
                name: "olej",
                amount: 50,
                unit: "ml"
            },
            {
                name: "woda",
                amount: 500,
                unit: "ml"
            }
        ],
        steps: [ "Warzywa obierz i ugotuj do miękkości", "Po ugotowaniu drobno je posiekaj i dopraw, farsz musi być wyrazisty", "Do miski wsyp przesianą mąkę, wlej olej i powoli zalej wrzątkiem ciągle mieszając", "Odstaw na 5 min żeby ciasto dobrze się zaparzyło", "Zaparzone ciasto przełóż na stolnicę i wyrabiaj do uzyskania gładkości i elastyczności", "Ciasto przykryj folią i odstaw na 20 min aby odpoczęło", "Ciasto rozwałkuj na grubość 2 mm, szklanką wykrój okręgi, a następnie na każdy nałóż łyżeczkę farszu, zwiń na pół i sklej mocno palcami", ", Pierożki ugotuj w osolonej wodzie lub usmaż na patelni" ]
    },
    {
        _id: ObjectId("3fadf722f3568bb446dd84b6"),
        name: "Gulasz z białą fasolą",
        portions: 3,
        prepare_time: 60,
        max_storage_time: 2,
        diet: "wegetariańska",
        ingredients: [
            {
                name: "fasola",
                amount: 120,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 3,
                unit: "szt"
            },
            {
                name: "marchewka",
                amount: 4,
                unit: "szt"
            },
            {
                name: "pieczarki",
                amount: 140,
                unit: "g"
            },
            {
                name: "oliwa",
                amount: 50,
                unit: "ml"
            },
            {
                name: "papryka",
                amount: 2,
                unit: "szt"
            },
            {
                name: "woda",
                amount: 250,
                unit: "ml"
            },
            {
                name: "kasza gryczana",
                amount: 150,
                unit: "g"
            }
        ],
        steps: [ "Fasolę zalej zimną wodą i pozostaw w misce na 2-3 godziny, następnie gotuj ją przez 50 min", ", Przygotuj sos, gulasz wymieszaj z wodą", "Na patelni, na oliwie z oliwek, podsmaż posiekaną w drobną kostkę cebulę, następnie dodaj pokrojone w plastry pieczarki", "Całość smaż przez 1 min, cały czas mieszając", "Następnie dodaj cienkie plastry marchewki i pokrojoną w kostkę paprykę", "Smaż chwilę razem", "Zalej przygotowanym sosem Knorr, wymieszaj dobrze i duś przez ok", "10 min", "Pod koniec duszenia dodaj wcześniej ugotowaną fasolę, posiekaną natkę i dokładnie mieszając, duś przez następne 3 min", "Kaszę gryczaną ugotuj w/g instrukcji na opakowaniu", "Gotowy gulasz podawaj z kaszą" ]
    },
    {
        _id: ObjectId("a4ed346a766a35fea956ba0c"),
        name: "Mielone wegetariańskie w sosie pieczarkowym",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "wegetariańska",
        ingredients: [
            {
                name: "pieczarki",
                amount: 500,
                unit: "g"
            },
            {
                name: "kasza jęczmienna",
                amount: 100,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "czosnek",
                amount: 4,
                unit: "ząbki"
            },
            {
                name: "śmietana",
                amount: 250,
                unit: "ml"
            },
            {
                name: "woda",
                amount: 100,
                unit: "ml"
            },
            {
                name: "nataka pietruszki",
                amount: 1,
                unit: "szt"
            },
            {
                name: "jajka",
                amount: 2,
                unit: "szt"
            },
            {
                name: "olej",
                amount: 100,
                unit: "ml"
            }
        ],
        steps: [ "Kaszę wykorzystałam z poprzedniego obiadu, dzień wcześniej ugotowaną", "Pieczarki umyte i osuszone ścieram na tarce jarzynowej", "Cebulę i czosnek drobno siekam i przesmażam na oleju", "Dodaję pieczarki i smażę, aż woda z nich wyparuje", "Do miski przekładam kaszę, dodaję pieczarki przesmażone i ostudzone, wbijam jajko, dodaję natkę posiekaną, pieprz", "Całość mieszam łyżką i formuję klopsy w dłoni", "Obtaczam w panierce z ciecierzycy", "Smażę na oleju  rzepakowym", "Sos: Do miseczki wlewam wodę, dodaję śmietank  i mieszam", "Przelewam do garnka i zagotowuję", "Klopsiki podaję z sosem", "Można do obiadu podać ogórki konserwowe" ]
    },
    {
        _id: ObjectId("ad551ffacf3b2788f21e5499"),
        name: "Steki z indyka",
        portions: 4,
        prepare_time: 45,
        max_storage_time: 2,
        diet: "mięsna",
        ingredients: [
            {
                name: "pierś z indyka",
                amount: 700,
                unit: "g"
            },
            {
                name: "ananas z puszki",
                amount: 250,
                unit: "g"
            },
            {
                name: "ser żółty",
                amount: 25,
                unit: "g"
            },
            {
                name: "natka pietruszki",
                amount: 1,
                unit: "szt"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "pomidory z puszki",
                amount: 700,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "czosnek",
                amount: 1,
                unit: "ząbki"
            },
            {
                name: "oliwa",
                amount: 50,
                unit: "ml"
            },
            {
                name: "jajka",
                amount: 2,
                unit: "szt"
            }
        ],
        steps: [ "Sznycle natnij ostrożnie z boku tak, aby powstały kieszonki", "Ananasa pokrój w drobną kostkę i wymieszaj z serem oraz natką", "Przygotowanym farszem napełnij kieszonki", "Jajka wymieszaj z 2 łyżkami mąki", "Gotowe, nafaszerowane steki oprósz mąką, obtocz w jajku i obsmaż z obu stron na złoty kolor", "Następnie zdejmij z patelni i osusz na papierowym ręczniku", "W garnku przesmaż cebulę oraz czosnek, a następnie dodaj pomidory z puszki", "Całość zagotuj", "Pomidorowy sos przelej do żaroodpornego naczynia, na nim ułóż obsmażone steki", "Naczynie przykryj i wstaw do nagrzanego do 180 °C piekarnika na 25-30 minut", "Gotowe danie podawaj z ryżem" ]
    },
    {
        _id: ObjectId("fa9585aecedea2a8bd69e12b"),
        name: "Ziemniaczana zapiekanka z kurczakiem",
        portions: 4,
        prepare_time: 60,
        max_storage_time: 2,
        diet: "mięsna",
        ingredients: [
            {
                name: "ziemniaki",
                amount: 12,
                unit: "szt"
            },
            {
                name: "śmietana",
                amount: 300,
                unit: "ml"
            },
            {
                name: "olej",
                amount: 80,
                unit: "ml"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "natka pietruszki",
                amount: 1,
                unit: "szt"
            },
            {
                name: "pierś z kurczaka",
                amount: 400,
                unit: "g"
            }
        ],
        steps: [ "Cebulę pokrój w piórka, podsmaż ją w garnku, dodaj pokrojonego w kostkę kurczaka, smaż chwilę, dodaj pokrojone w plastry ziemniaki", "Całość smaż mieszając co jakiś czas, aż ziemniaki delikatnie się rozkleją", "Dodaj wtedy śmietanę wymieszaną z fixem, całość zagotuj, a następnie przełóż w żaroodporne naczynie uciskając wszystkie składniki razem", "Zapiekankę z wierzchu posyp serem, zawiń w folię aluminiową, wstaw do nagrzanego do 190°C piekarnika na 40 minut", "Dziesięć minut przed końcem pieczenia usuń folię, aby ser mógł się zapiec", "Podawaj z posiekaną natką pietruszki" ]
    },
    {
        _id: ObjectId("5a8094dd30a944ac30139140"),
        name: "Schab po meksykańsku",
        portions: 2,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "mięsna",
        ingredients: [
            {
                name: "schab",
                amount: 150,
                unit: "g"
            },
            {
                name: "papryka żółta",
                amount: 2,
                unit: "szt"
            },
            {
                name: "cukinia",
                amount: 2,
                unit: "szt"
            },
            {
                name: "kukurydza z puszki",
                amount: 200,
                unit: "g"
            },
            {
                name: "czosnek",
                amount: 1,
                unit: "szt"
            },
            {
                name: "ryż",
                amount: 200,
                unit: "g"
            },
            {
                name: "olej",
                amount: 20,
                unit: "ml"
            },
            {
                name: "woda",
                amount: 300,
                unit: "ml"
            }
        ],
        steps: [ "Przygotowanie schabu po meksykańsku zacznij od pokrojenia mięsa na paski", "Następnie rozgrzej na patelni łyżkę oleju i podsmaż na niej mięso przez około 2-3 minuty na stronę", "Żółtą paprykę oraz cukinię pokrój w paski, a ząbek czosnku rozetrzyj na tarce (możesz też przecisnąć go przez praskę lub posiekać nożem)", "Następnie dosyp te warzywa do mięsa na patelni", "Do całości dodaj także łyżkę odsączonej kukurydzy i duś pod przykryciem przez kilka minut", "Do małego naczynia wlej 300 ml zimnej wody i wymieszaj z nią Fix Chili Con Carne Knorr", "Przelej mieszaninę na patelnię z mięsem i gotuj danie jeszcze przez 10 minut", "Podawaj schab po meksykańsku z ryżem ulubionego typu, np", "basmati, białym lub jaśminowym" ]
    },
    {
        _id: ObjectId("6cff13a8199f5a8b25f1fa0f"),
        name: "Tortilla z kurczakiem z grilla",
        portions: 4,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "mięsna",
        ingredients: [
            {
                name: "pierś z kurczaka",
                amount: 800,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "papryka czerwona",
                amount: 1,
                unit: "szt"
            },
            {
                name: "żółty ser",
                amount: 50,
                unit: "g"
            },
            {
                name: "placki tortilli",
                amount: 4,
                unit: "szt"
            },
            {
                name: "oliwa",
                amount: 30,
                unit: "ml"
            }
        ],
        steps: [ "Piersi z kurczaka umyj pod bieżącą wodą i osusz papierowym ręcznikiem", "Następnie przygotuj deskę do krojenia, pokrój w cienkie paski i przełóż do miski", "Do kurczaka dodaj Przyprawę gyros Knorr i dwie łyżki oliwy", "Całość dokładnie wymieszaj i odstaw na kilka minut w chłodne miejsce", "Warzywa umyj, osusz i pokrój – paprykę w cienkie paski, a cebulę w piórka", "Na patelni rozgrzej olej, dodaj cebulę, paprykę i mięso", "Usmaż wszystkie składniki i odstaw", "Kiedy farsz nieco ostygnie, dodaj do niego starty na grubych oczkach żółty ser i dokładnie wymieszaj wszystkie składniki", "Gotowe placki tortilli wyłóż na kuchenny blat i nałóż na nie farsz", "Zwiń jak naleśniki, zaczynając od zawinięcia boków do środka tak, aby farsz nie wydostał się na zewnątrz", "Tortille opiekaj na rozgrzanym grillu po 3-4 minuty z każdej strony, aż ser w środku się rozpuści", "Tortilla z kurczakiem dobrze smakuje podawana z kwaśną śmietaną i sałatą", "Jeśli lubisz ostre i aromatyczne potrawy, możesz dodać do farszu papryczkę chili oraz posiekaną świeżą kolendrę" ]
    },
    {
        _id: ObjectId("dfdc154850adf3483bc6adc8"),
        name: "Zapiekanka Gyros",
        portions: 4,
        prepare_time: 45,
        max_storage_time: 2,
        diet: "mięsna",
        ingredients: [
            {
                name: "pierś z kurczaka",
                amount: 700,
                unit: "g"
            },
            {
                name: "ser żółty",
                amount: 200,
                unit: "g"
            },
            {
                name: "papryka zielona",
                amount: 1,
                unit: "szt"
            },
            {
                name: "papryka żółta",
                amount: 1,
                unit: "szt"
            },
            {
                name: "papryka czerwona",
                amount: 1,
                unit: "szt"
            },
            {
                name: "czerwona cebula",
                amount: 2,
                unit: "szt"
            },
            {
                name: "papryczka chilli",
                amount: 1,
                unit: "szt"
            },
            {
                name: "olej",
                amount: 30,
                unit: "ml"
            }
        ],
        steps: [ "Kurczaka pokrój w paski, wymieszaj z olejem", "Papryki pokrój w paski, cebule w piórka, czosnek i chilli posiekaj", "Na patelni smaż kurczaka, następnie dodaj warzywa", "Smaż jeszcze przez kilka minut", "Całość przełóż do naczynia do zapiekania", "Gyrosa posyp z wierzchu obficie startym żółtym serem i zapiekaj w rozgrzanym do 180 stopni piekarniku przez 15 minut", "Podawaj posypane posiekaną kolendrą" ]
    },
    {
        _id: ObjectId("f4fd4775a5579e8ac7858cec"),
        name: "Makaron z kurkami i śmietanką",
        portions: 3,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "bezglutenowa",
        ingredients: [
            {
                name: "makaron",
                amount: 250,
                unit: "g"
            },
            {
                name: "cebula",
                amount: 20,
                unit: "g"
            },
            {
                name: "oliwa",
                amount: 20,
                unit: "ml"
            },
            {
                name: "kurki",
                amount: 200,
                unit: "g"
            },
            {
                name: "cytryna",
                amount: 1,
                unit: "szt"
            },
            {
                name: "ziarna słonecznika",
                amount: 100,
                unit: "g"
            }
        ],
        steps: [
            "Zagotuj wodę i zalej nią ziarna słonecznika, pozostaw na kwadrans.",
            "Gotuj makaron w osolonej wodzie. Pokrój cebulę, przygotuj kurki.",
            "Przygotuj śmietankę z namoczonego słonecznika, wody, soku z cytryny i soli.",
            "Przygotuj sos: na rozgrzanej oliwie podsmaż cebulę, dodaj kurki, smaż z sokiem z cytryny.",
            "Połącz składniki: dodaj śmietankę, pietruszkę, sól, pieprz i opcjonalnie płatki drożdżowe do kurek. Dodaj makaron, podgrzewaj i podawaj."
        ]
    },
    {
        _id: ObjectId("965fa451b2b3cf16baaa2763"),
        name: "Risotto z dynią",
        portions: 2,
        prepare_time: 30,
        max_storage_time: 2,
        diet: "bezglutenowa",
        ingredients: [
            {
                name: "oliwa",
                amount: 20,
                unit: "ml"
            },
            {
                name: "dynia",
                amount: 500,
                unit: "g"
            },
            {
                name: "ryż",
                amount: 150,
                unit: "g"
            },
            {
                name: "bulion",
                amount: 1,
                unit: "l"
            },
            {
                name: "wino",
                amount: 50,
                unit: "ml"
            },
            {
                name: "cebula",
                amount: 1,
                unit: "szt"
            },
            {
                name: "czosnek",
                amount: 1,
                unit: "ząbki"
            }
        ],
        steps: [
            "Pokroić cebulę i czosnek, obrać i pokroić dynię. Bulion podgrzewać na małym ogniu.",
            "Na patelni rozgrzać oliwę, dodać cebulę, czosnek, smażyć 1 minutę, dodać dynię, smażyć 2-3 minuty, dodać ryż i wymieszać.",
            "Dolać wino do ryżu, odparować, następnie stopniowo dolać bulion, gotować 18-20 minut aż do uzyskania ryżu al dente.",
            "Jarmuż blanszować przez 1 minutę w bulionie, schłodzić w zimnej wodzie, pokroić na paski.",
            "Risotto doprawić gałką muszkatołową, płatkami drożdżowymi, wymieszać, posypać pieprzem, dodać jarmuż, skropić oliwą, podawać."
        ]
    }
]);