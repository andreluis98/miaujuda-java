INSERT INTO users (name, email, username, password)
SELECT 'Doe J', 'doe@example.com', 'doejohn', 'securePassword12345'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'doejohn'
    OR email = 'doe@example.com'
    OR name = 'Doe J'
);



INSERT INTO pets (name, gender, status, address, observation, pet, user_id)
SELECT 'Rexxxx', 'Macho', 'Perdido', 'Rua B, 123', 'Cão amigável', 'Cachorro',
       (SELECT id FROM users WHERE username = 'doejohn')
WHERE NOT EXISTS (
    SELECT 1 FROM pets
    WHERE (name, address) = ('Rexxxx', 'Rua B, 123')
);


