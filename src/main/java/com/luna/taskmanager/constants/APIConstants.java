package com.luna.taskmanager.constants;

public class APIConstants {
    package com.luna.taskmanager.constants;

    /**
     * This class defines constants used in the API.
     * It includes endpoints and configuration parameters for the API.
     */
    public class APIConstants {

        // Base endpoint for the API
        public static final String API = "api";

        // Version identifier for the API
        public static final String VERSION_1 = "v1";

        // Endpoint for accessing task lists
        public static final String TASKLISTS_ENDPOINT = "tasklists";

        // Endpoint for accessing a single task list
        public static final String TASKLIST_ENDPOINT = "tasklist";

        // Endpoint for accessing tasks within a task list
        public static final String TASKS_ENDPOINT = "tasks";

        // Maximum allowed length for the name of a task or task list
        public static final int MAXIMUM_LENGTH_FOR_NAME = 64;

        // Maximum allowed length for the description of a task or task list
        public static final int MAXIMUM_LENGTH_FOR_DESCRIPTION = 256;

    }
}
