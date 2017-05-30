using UnityEngine;
using System.Collections;

public class ChangeDaytime : MonoBehaviour {
    
    private float targetX;
    private bool inCamera;
    private bool keypressed;
	// Use this for initialization
	void Start () {
        targetX = -38f;
        keypressed = false;
    }

    // Update is called once per frame
    void Update()
    {
        float dt = Time.deltaTime;
        if (Input.GetKey(KeyCode.Space))
        {
            if (transform.position.x >= -30)
                inCamera = true;
            else
                inCamera = false;
            keypressed = true;
        }
        if (keypressed && inCamera)
        {
            if (transform.position.x >= targetX)
                transform.position = transform.position + Vector3.left * 10.0f * dt;
            else
                keypressed = false;
        }
        
    }
}
