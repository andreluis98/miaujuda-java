INSERT INTO users (name, email, username, password)
SELECT 'John Doe', 'john@example.com', 'johndoe', 'securePassword123'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'johndoe');


INSERT INTO pets (name, gender, status, address, observation, pet, user_id)
SELECT 'Rex', 'Macho', 'Perdido', 'Rua A, 123', 'Cão amigável', 'Cachorro',
       (SELECT id FROM users WHERE username = 'johndoe')
WHERE NOT EXISTS (
    SELECT 1 FROM pets WHERE name = 'Rex' 
    AND user_id = (SELECT id FROM users WHERE username = 'johndoe')
);
