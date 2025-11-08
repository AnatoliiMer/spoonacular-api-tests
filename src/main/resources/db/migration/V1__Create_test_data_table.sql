CREATE TABLE IF NOT EXISTS test_data (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    test_name TEXT NOT NULL,
    api_endpoint TEXT NOT NULL,
    request_data TEXT,
    expected_response TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO test_data (test_name, api_endpoint, request_data, expected_response)
VALUES
('search_recipes', '/recipes/complexSearch', '{"query":"pasta","number":2}', '{"status":"success"}'),
('get_recipe_info', '/recipes/{id}/information', '{"id":716429}', '{"status":"success"}'),
('analyze_recipe', '/recipes/analyze', '{"title":"Test recipe","ingredients":["flour","water"]}', '{"status":"success"}'),
('food_trivia', '/food/trivia/random', '{}', '{"status":"success"}'),
('ingredient_search', '/food/ingredients/search', '{"query":"apple","number":1}', '{"status":"success"}');